export interface ShowcaseWorkInfo{
    workName : String, 
    workMinPlace : String, 
    workMaxPlace : String, 
    workCoverPath?: String, 
    workVideoPath: String
}

export interface ShowcaseBoxInfo{
    authorName?: string | undefined,
    commentNum?: String | undefined,
    honor?: string[] | undefined,
    brief?: string | undefined,
    works?: ShowcaseWorkInfo[] | undefined,
}