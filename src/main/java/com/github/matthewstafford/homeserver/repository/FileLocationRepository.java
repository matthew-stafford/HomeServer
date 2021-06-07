package com.github.matthewstafford.homeserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.matthewstafford.homeserver.entity.FileLocation;

@Repository
public interface FileLocationRepository extends CrudRepository<FileLocation, Integer> {

}
