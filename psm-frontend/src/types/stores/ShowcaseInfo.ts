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
    authorBrief?:string,
    profile?:string,
    author_id?:String,
    commentNum?:String,
    works?:String,
    modifyTime?: String,
    primarySort?: String,
    lastSort?: String,
    isIdle?: String,
    canQuick?: String,
}