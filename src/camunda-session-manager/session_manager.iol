type SessionType: void {
	.nextUser: string
	.processId: string
	.state: string
}

type SessionElementAdd: void {
	.nextUser: string
	.processId: string
	.state: string
}

type SessionAddResponse: void {
	.message: string
}

type SessionElementRemove: void {
	.processId: string
}

type SessionRemoveResponse: void {
	.message: string
}

type SessionElementGet: void {
	.username: string
}

type SessionGetResponse: void {
	.sessions[0, *]: SessionType
	.message: string
}


interface SessionManager {
	RequestResponse:
		addSession(SessionElementAdd)(SessionAddResponse),
		removeSession(SessionElementRemove)(SessionRemoveResponse),
		getSessions(SessionElementGet)(SessionGetResponse)
}