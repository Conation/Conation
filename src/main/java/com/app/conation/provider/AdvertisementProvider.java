package com.app.conation.provider;

import com.app.conation.domain.Advertisement;
import com.app.conation.repository.AdvertisementRepository;
import com.app.conation.domain.State;
import com.app.conation.enums.AdvertisementCategory;
import com.app.conation.advice.exceptions.BaseException;
import com.app.conation.response.BaseResponseStatus;
import com.app.conation.response.dto.GetAdvertisementRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Transactional(readOnly = true)
@Service
public class AdvertisementProvider {

    private final AdvertisementRepository advertisementRepository;

    @Autowired
    public AdvertisementProvider(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public List<GetAdvertisementRes> retrieveAdvertisements(Pageable pageable, AdvertisementCategory category) {
        Page<Advertisement> advertisements = null;
        if (category == null) {
            advertisements = advertisementRepository.findAdvertisements(pageable, State.ACTIVE);
        }
        if (category != null) {
            advertisements = advertisementRepository.findAdvertisementsInCategory(pageable, category, State.ACTIVE);
        }
        return advertisements.stream()
                .map(Advertisement::toGetAdvertisementRes)
                .collect(toList());
    }

    public Advertisement getAdvertisementById(Long advertisementId) {
        Optional<Advertisement> optionalAdvertisement = advertisementRepository.findById(advertisementId);
        if (optionalAdvertisement.isEmpty()) {
            throw new BaseException(BaseResponseStatus.NOT_FOUND_ADVERTISEMENT);
        }
        return optionalAdvertisement.get();
    }
}
