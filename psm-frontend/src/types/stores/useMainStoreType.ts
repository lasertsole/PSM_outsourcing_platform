export interface useMainStoreObjInfoType{
    userName: string|undefined,
    userProfile: undefined|string,
    userID: undefined|string,
}

export interface useMainStoreObjType{
    token: string|undefined,
    userinfo: useMainStoreObjInfoType,
    isOnline: boolean,
}