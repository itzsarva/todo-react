import {LOGIN_API_URL} from '../util/TodoConstants';
import {post} from '../util/RestClient';

class AuthService {

  login(wfi) {
      return post(LOGIN_API_URL + "login", wfi)
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(username, email, password) {
    return post(API_URL + "logout", {
      username,
      email,
      password
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();
