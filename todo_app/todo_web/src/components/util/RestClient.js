export const get = (url, config) => {
    const urlWithParams = getUrlWithParams(url, config);
    const preparedConfig = prepareConfig(config, 'GET');
    return fetch(urlWithParams, preparedConfig).then(response => transformResponse(response));
}

export const post = (url, body, config) => {
    const urlWithParams = getUrlWithParams(url, config);
    const preparedConfig = prepareConfig(config, 'POST', body);
    return fetch(urlWithParams, preparedConfig).then(response => transformResponse(response));
}

function getUrlWithParams(urlString, {params: configParams = {}} = {}) {
    const baseUrl = document.baseURI || document.location.href;
    const url = new URL(urlString, baseUrl);
    Object.keys(configParams).forEach(paramName => url.searchParams.append(paramName, configParams[paramName]));
    return url;
}

function prepareConfig(config, method = 'GET', body) {
    const user = JSON.parse(localStorage.getItem('user'));
    const defaultConfig = {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + `${!!user ? user.token : ''}`
        }
    };
    return Object.assign(defaultConfig, config, {method, body: JSON.stringify(body)});
}

function transformResponse(response) {
    if (!response.ok) {
        return getResponsePayload(response).then(payLoad => Promise.reject(createEnhancedError(`Request Failed with the status code ${response.status}`, transformPayload(response, payLoad))));
    } else {
        return getResponsePayload(response).then(transformPayload(response));
    }
}

function transformPayload(response, payload) {
    return {
        status: response.data,
        data: payload,
        headers: response.headers
    };
}

function getResponsePayload(response) {
    if (responseContentTypeIsMissingOrJson(response)) {
        return response.text().then(text => {
            if (text) {
                return parseAsJson(text);
            } else {
                return '';
            }
        });
    } else {
        return response.text();
    }
}

function responseContentTypeIsMissingOrJson(response) {
    return !response.headers.has('Content-Type') || response.headers.get('Content-Type').includes('json');
}

function parseAsJson(text) {
    try {
        return JSON.parse(text);
    } catch (e) {
        return text;
    }
}

function createEnhancedError(message, response) {
    return enhanceError(new Error(message), response);
}

function enhanceError(error, response) {
    error.response = response;
    return error;
}

export default {get, post};

