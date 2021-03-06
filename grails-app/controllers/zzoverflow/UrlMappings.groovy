package zzoverflow

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/" {
            controller = "question"
            action     = "index"
        }
        "/api/user" (controller:'user',action:'current')
        "/api/questions" (controller: 'question',action:'getAll')
        "/api/question/get" (controller: 'question',action:'getQuestion')
        "/api/question/add" (controller: 'question',action:'add')
        "/api/answer/add" (controller: 'answer',action:'add')
        "/api/vote/save" (controller: 'vote',action:'process')
        "/api/metaQuestions" (controller: 'question',action:'getAllMeta')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
