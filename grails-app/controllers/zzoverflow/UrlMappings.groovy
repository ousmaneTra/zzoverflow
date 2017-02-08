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
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
