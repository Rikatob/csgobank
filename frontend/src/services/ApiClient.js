class ApiClient {

  static SERVER_URL = 'http://localhost:8080';
  static GET_VAULT = '/vault';

  static vault() {
    return fetch(ApiClient.SERVER_URL + ApiClient.GET_VAULT)
  }
}

export default ApiClient;