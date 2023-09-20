export interface ShowcaseWorkInfo{
    workName : String, 
    workMinPlace : String, 
    workMaxPlace : String, 
    workCoverPath?: String, 
    workVideoPath: String
}

export interface ShowcaseBoxesInfo{
    id?:String,
    userName?:string,
    honor?:string,
    author_brief?:string,
    profile?:string,
    author_id?:String,
    commentNum?:String,
    works?:String,
    modify_time?: String,
    primarySort?: String,
    lastSort?: String,
    isIdle?: String,
    canQuick?: String,
}