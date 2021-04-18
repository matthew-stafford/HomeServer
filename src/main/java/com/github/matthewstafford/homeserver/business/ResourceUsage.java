package com.github.matthewstafford.homeserver.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.github.matthewstafford.homeserver.beans.ResourceDiskBean;
import com.github.matthewstafford.homeserver.beans.ResourcePartition;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OSFileStore;

@Component
public class ResourceUsage {

	private static SystemInfo si = new SystemInfo();
	private static HardwareAbstractionLayer hal = si.getHardware();
	private static CentralProcessor cpu = hal.getProcessor();
	private static long[] PREV_TICKS = new long[CentralProcessor.TickType.values().length];
	private static long TIME_LAST_UPDATED = 0;
	private static int TIME_BETWEEN_UPDATES = 1000; // 1 seconds
	private static int TIME_BETWEEN_DISK_REFRESHES = 60000; //60 seconds
	private static long TIME_LAST_REFRESHED_DISKS = 0;

	public static double getCPU() {
		double cpuLoad = cpu.getSystemCpuLoadBetweenTicks(PREV_TICKS) * 100;
		PREV_TICKS = cpu.getSystemCpuLoadTicks();
		return cpuLoad;
	}

	private static boolean canUpdate() {
		return (System.currentTimeMillis() - TIME_LAST_UPDATED > TIME_BETWEEN_UPDATES);
	}

	private static boolean canUpdateDisks() {
		return (System.currentTimeMillis() - TIME_LAST_REFRESHED_DISKS > TIME_BETWEEN_DISK_REFRESHES);
	}

	private String cpuName;
	private double cpuUsage;
	private long uptime;
	private long bootTime;
	private long availableMemory;
	private long totalMemory;
	private HashMap<String, ResourceDiskBean> disks;

	public ResourceUsage() {
		cpuName = cpu.getProcessorIdentifier().getName();
		totalMemory = hal.getMemory().getTotal();
		bootTime = si.getOperatingSystem().getSystemBootTime();
	}

	public double getCpuUsage() {
		update();
		return cpuUsage;
	}

	public HashMap<String, ResourceDiskBean> getDisks() {
		return disks;
	}

	public void setDisks(HashMap<String, ResourceDiskBean> disks) {
		this.disks = disks;
	}

	public void update() {
		if (canUpdate()) {
			cpuUsage = getCPU();
			availableMemory = hal.getMemory().getAvailable();
			uptime = si.getOperatingSystem().getSystemUptime();

			if (canUpdateDisks()) {
				HashMap<String, ResourceDiskBean> disks = new HashMap<String, ResourceDiskBean>();

				// list disks and partitions
				for (HWDiskStore store : hal.getDiskStores()) {
					disks.put(store.getName(), new ResourceDiskBean(store.getName()));
					disks.get(store.getName()).setSize(store.getSize());
					for (HWPartition part : store.getPartitions()) {
						disks.get(store.getName()).getPartitions().put(part.getIdentification(), new ResourcePartition());
					}

				}

				// get storage data (free/used space + model numbers)
				for (OSFileStore store : si.getOperatingSystem().getFileSystem().getFileStores()) {
					// find disk for each store
					for (Map.Entry<String, ResourceDiskBean> d : disks.entrySet()) {
						// if disk contains partition
						if (d.getValue().getPartitions().containsKey(store.getName())) {
							// add data
							d.getValue().setFreeSpace(store.getFreeSpace());
							d.getValue().getPartitions().get(store.getName()).setSize(store.getTotalSpace());
							d.getValue().getPartitions().get(store.getName()).setName(store.getName());
							d.getValue().getPartitions().get(store.getName()).setFreeSpace(store.getUsableSpace());

							break;
						}
					}
				}

				setDisks(disks);
			}

			TIME_LAST_UPDATED = System.currentTimeMillis();
		}
	}

	public long getUptime() {
		return uptime;
	}

	public void setUptime(long uptime) {
		this.uptime = uptime;
	}

	public long getBootTime() {
		return bootTime;
	}

	public void setBootTime(long bootTime) {
		this.bootTime = bootTime;
	}

	public void setCpuUsage(double cpuUsage) {
		this.cpuUsage = cpuUsage;
	}

	public long getAvailableMemory() {
		return availableMemory;
	}

	public void setAvailableMemory(long availableMemory) {
		this.availableMemory = availableMemory;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public String getCpuName() {
		return cpuName;
	}

	public void setCpuName(String cpuName) {
		this.cpuName = cpuName;
	}
}
