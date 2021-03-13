package com.app.conation.provider;

import com.app.conation.domain.Region;
import com.app.conation.repository.RegionRepository;
import com.app.conation.advice.exceptions.BaseException;
import com.app.conation.response.BaseResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
@Service
public class RegionProvider {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionProvider(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Region getRegionById(Long regionId) {
        Optional<Region> region = regionRepository.findById(regionId);
        if (region.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_REGION);
        }
        return region.get();
    }
}
