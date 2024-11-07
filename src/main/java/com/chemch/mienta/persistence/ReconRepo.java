package com.chemch.mienta.persistence;

import com.chemch.mienta.model.Recon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource(collectionResourceRel = "recon", path = "recon")
public interface ReconRepo extends CrudRepository<Recon, UUID> {
    Recon findReconById(UUID id);
}