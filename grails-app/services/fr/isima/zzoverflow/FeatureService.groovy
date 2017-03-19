package fr.isima.zzoverflow

import grails.transaction.Transactional
import org.springframework.beans.factory.annotation.*

@Transactional
class FeatureService {
    @Value('${feature.registration.enabled}')
    boolean registrationFeature

    @Value('${feature.askquestion.enabled}')
    boolean askQuestionFeature

    
    def isEnable(String feature) {
        boolean result = false 
        switch(feature) {
            case "registration":
                result = registrationFeature
                break
            case "askQuestion":
                result = askQuestionFeature
                break
            default : 
                break
        }
        return result
    }
}
