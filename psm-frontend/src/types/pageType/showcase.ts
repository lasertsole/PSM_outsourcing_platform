export interface optionInfo{
    label:string,
    value:number,
}

export enum PrimarySort{
    "剪辑类" = 0,
    "美工类" = 1,
    "翻译类" = 2,
    "字幕类" = 3,
}

export enum SortWay{
    "按时间" = 0,
    "按评论数" = 1,
}