export interface itemBoxInfo{
    userName?:string,
    honor?:string,
    author_brief?:string,
    profile?:string,
    author_id?:String,
    commentNum?:number,
    works?:{abstract:string, price:string, imgPath:string}[],
    modify_time?: String,
    primarySort?: Number,
    lastSort?: Number,
    isIdle?: Number,
    canQuick?: Number,
}