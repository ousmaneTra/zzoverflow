package fr.isima.zzoverflow

import grails.transaction.Transactional

@Transactional
class TagService {

    def makeTagList(list) {
        def tagList = []

        if(list)
            if (list.class.isArray()){
                for(tagName in list){
                    def tag = Tag.findOrCreateByName(tagName.toLowerCase()).save()
                    tagList << tag.id
                }
            }else{
                def tag = Tag.findOrCreateByName(list.toLowerCase()).save()
                tagList << tag.id
            }

        return tagList
    }

}
