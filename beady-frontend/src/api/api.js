export var Api = {
	get(context, path, onSuccess, onError) {
		Api.getWithParam(context, path, {}, onSuccess, onError);
	},

	getWithParam(context, path, data, onSuccess, onError) {
		Api.request(context, path, 'GET', data, onSuccess, onError);
	},

	post(context, path, data, onSuccess, onError) {
		Api.request(context, path, 'POST', data, onSuccess, onError)
	},

	delete(context, path, data, onSuccess, onError) {
		Api.request(context, path, 'DELETE', data, onSuccess, onError)
	},

	put(context, path, data, onSuccess, onError) {
		Api.request(context, path, 'PUT', data, onSuccess, onError)
	},

	request(context, path, method, data, onSuccess, onError) {
		const url = process.env.VUE_APP_API_URL + path;

		const successHandler = response => {
			context.errorResponse = undefined;
			context.submitSuccess = true;

			if (typeof onSuccess === "function") {
				onSuccess(response.body);
			}
		};

		const errorHandler = errorResponse => {
			context.errorResponse = errorResponse.body;
			context.submitSuccess = false;

			if (typeof onError === "function") {
				onError(errorResponse.body);
			}
		};

		if (method === 'POST') {
			context.$http.post(url, data).then(successHandler, errorHandler);
		} else if (method === 'GET') {
			context.$http.get(url, {params: data}).then(successHandler, errorHandler);
		} else if (method === 'DELETE') {
			context.$http.delete(url, {body: data}).then(successHandler, errorHandler);
		} else if (method === 'PUT') {
			context.$http.put(url, data).then(successHandler, errorHandler);
		}
	}
}