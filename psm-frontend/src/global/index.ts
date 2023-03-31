import {getCurrentInstance} from 'vue'

export default function useGlobal(){
	return getCurrentInstance()?.appContext.app.config.globalProperties;
}