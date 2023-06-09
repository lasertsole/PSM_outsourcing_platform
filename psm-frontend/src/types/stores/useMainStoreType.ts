export interface useMainStoreObjInfoType{
    userName:string|undefined,
    userProfile:undefined|string,
    userID:undefined|string,
}

export interface useMainStoreObjType{
    token:undefined,
    userinfo: useMainStoreObjInfoType,
}