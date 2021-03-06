import React from 'react';
import ProgressBar from '../components/progressBar.jsx';

class ResourceUsage extends React.Component {
	
	constructor(props) {
		super(props);
		
		this.state = {
			data: {},
			error: null,
			isLoaded: false
		}
	}
	
	calculateDiskSize(usedBytes, totalBytes) {
		let scale = 0;
		let divisor = 1000;
		let scaleSizes = ["B", "KB", "MB", "GB", "TB", "PB"];
		let usedSize = usedBytes;
		let totalSize = totalBytes;
		
		// get the total size of disk so we know the scale		
		while (totalSize > 1000) {
			usedSize = usedSize / divisor;
			totalSize = totalSize / divisor;
			scale += 1;
		}
				
		return usedSize.toFixed(2) +"/"+totalSize.toFixed(2)+scaleSizes[scale];
	}

	secondsToUptimeString(s) {
		let days = Math.floor(s/86400);
		let hours = Math.floor( ((s - (86400 * days)) / 3600 ));
		let minutes = Math.floor( (((s - (86400 * days) ) - (3600 * hours)) / 60 ));
		let seconds = Math.floor( (((s - (86400 * days) ) - (3600 * hours)) - (60*minutes)));
		let uptimeString = (days > 0 ? days +'d ' : '');
		uptimeString += (hours > 0 ? hours +'h ' : '');
		uptimeString += (minutes > 0 ? minutes +'m ' : '');
		uptimeString += (seconds >= 0 ? seconds +'s ' : '');
		
		return uptimeString;

	}
	
	fetchData () {
		fetch("/api/resources")
	    .then(res => res.json())
	    .then(
	      (result) => {
	        this.setState({
	          isLoaded: true,
	          data: result
	        });
	      },
	      // Note: it's important to handle errors here
	      // instead of a catch() block so that we don't swallow
	      // exceptions from actual bugs in components.
	      (error) => {
			console.log(error);
	        this.setState({
	          isLoaded: true,
	          error
	        });
	      }
	    )
	}
	
	
	componentDidMount() {
		// grab initial data
		this.fetchData();
		
		// start interval to grab data every second
		this.interval = setInterval( () => {
			this.fetchData();
		}, 3000);
	}

	componentWillUnmount () {
		// tidy up interval on unmount
	  	clearInterval(this.interval);
	}
	
	render() {
		return (
			
			<div id="resourceUsage">
				<h1>Home Server</h1>
				<div className="serverStatusSeperator">
					<small>{this.state.isLoaded ? this.state.data.cpuName : 'CPU'}</small>
					<ProgressBar percentage={this.state.isLoaded ? this.state.data.cpuUsage.toFixed(0) : 0} />
				</div>
			
				<div className="serverStatusSeperator">
					<small>{this.state.isLoaded ? (this.state.data.availableMemory/1024/1024/1024).toFixed(0)+"/"+(this.state.data.totalMemory/1024/1024/1024).toFixed(0)+"GB RAM"  : 'RAM'}</small>
					<ProgressBar percentage={this.state.isLoaded ? ((this.state.data.availableMemory / this.state.data.totalMemory) * 100).toFixed(0) : 0} />
				</div>	
				
				{
					this.state.isLoaded ? 
						Object.keys(this.state.data.disks).map( (disk, index) => 						
							<div className="serverStatusSeperator" key={this.state.data.disks[disk].name}>
								<div style={{display:'flex', justifyContent:'space-between'}}>
									<small style={{textAlign:'left' }}>{this.state.data.disks[disk].name}</small>
									<small>{this.calculateDiskSize(this.state.data.disks[disk].size-this.state.data.disks[disk].freeSpace, this.state.data.disks[disk].size)}</small>
																
								</div>
								<ProgressBar percentage={this.state.isLoaded ? (((this.state.data.disks[disk].size-this.state.data.disks[disk].freeSpace) / this.state.data.disks[disk].size) * 100).toFixed(0) : 0} />
							</div>
						)
					: 
					''
				}
				<div className="serverStatusSeperator" style={{float:'bottom'}}>					
					<small>Uptime: {this.state.isLoaded ? this.secondsToUptimeString(this.state.data.uptime) : ''}</small>
				</div>
				
			</div>
		)
	}
}

export default ResourceUsage;