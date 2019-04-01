import Cookies from 'js-cookie'

const common = {
	state: {
		sidebar: {
			opened: !+Cookies.get('sidebarStatus'),
			active: Cookies.get('sidebarActive') || '',
			router: Cookies.get('routerActive')
		},
		competence: Cookies.get('competence')?JSON.parse(Cookies.get('competence')):null,
		routes: []
	},
	mutations: {
		COMPETENCE: (state, key) => {
			state.competence = key;
			Cookies.set('competence', key);
		},
		TOGGLE_SIDEBAR: state => {
			// 侧边栏打开,此时应该执行关闭操作
			if(state.sidebar.opened){
				Cookies.set('sidebarStatus', 1);
			}else{
				Cookies.set('sidebarStatus', 0);
			}
			state.sidebar.opened = !state.sidebar.opened;
		},
		CURRENT_SIDEBAR: (state, key) => {
			state.sidebar.active = key;
			Cookies.set('sidebarActive', key);
		},
		CURRENT_ROUTER: (state, key) => {
			state.sidebar.router = key;
			Cookies.set('routerActive', key);
		},
		GENERATE_ROUTES: (state, key) => {
			state.routes = key;
		}
	},
	actions: {
		Competence: ({commit}, key) => {
			commit('COMPETENCE', key)
		},
		ToggleSideBar: ({commit}) => {
			commit('TOGGLE_SIDEBAR')
		},
		CurrentSidebar: ({commit}, key) => {
			commit('CURRENT_SIDEBAR', key)
		},
		CurrentRouter: ({commit}, key) => {
			commit('CURRENT_ROUTER', key)
		},
		GenerateRoutes: ({commit}, key) => {
			commit('GENERATE_ROUTES', key)
		}
	}
}

export default common
