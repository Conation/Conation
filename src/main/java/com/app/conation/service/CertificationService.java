package com.app.conation.service;

import com.app.conation.domain.Certification;
import com.app.conation.repository.CertificationRepository;
import com.app.conation.domain.draw.MessageSender;
import com.app.conation.domain.draw.UserMessageParameters;
import com.app.conation.advice.exceptions.NotFoundCertificationException;
import com.app.conation.requestdto.CertificatePhoneNumberReq;
import com.app.conation.requestdto.ValidatePhoneNumberReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class CertificationService {

    private final CertificationRepository certificationRepository;
    private final MessageSender messageSender;

    public Long sendCertificationMessage(CertificatePhoneNumberReq request) {
        UserMessageParameters userMessageParameters = messageSender.userPhoneNumberSettingToCertification(request.getPhoneNumber());
        String certificationNumber = userMessageParameters.getParameters().get("text");
        Certification certification = Certification.builder()
                .certificationNumber(certificationNumber)
                .build();
        Certification save = certificationRepository.save(certification);
        messageSender.sendSMS(userMessageParameters);
        return save.getId();
    }

    public Boolean validateCertification(ValidatePhoneNumberReq request) {
        Optional<Certification> optionalCertification = certificationRepository.findById(request.getCertificationId());
        optionalCertification.orElseThrow(() -> new NotFoundCertificationException("존재하지 않는 인증입니다."));
        if (optionalCertification.get().getCertificationNumber().equals(request.getCertificationNumber())) {
            return true;
        }
        return false;
    }
}
