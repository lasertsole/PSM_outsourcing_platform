export interface useMainStoreObjInfoType{
    userName:string|undefined,
    userProfile:undefined|string
}

export interface useMainStoreObjType{
    token:undefined,
    userinfo: useMainStoreObjInfoType,
}