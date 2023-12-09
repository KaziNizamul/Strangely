import { EMPTY_OBJECT, EMPTY_STRING } from '../../../../resources/shared/global.constant';
import Utility from '../../../../utils/Utility';

class LoginSignupUtils {
  static getSignUpPayload = ({
    name = EMPTY_STRING,
    email = EMPTY_STRING,
    password = EMPTY_STRING,
    phoneNumber = EMPTY_STRING,
  }) => {
    const {
      firstName = EMPTY_STRING,
      lastName = EMPTY_STRING,
    } = Utility.getSplitString(name, 'firstName', 'lastName');
    return {
      username: name,
      password,
      phone: phoneNumber,
      email,
      firstName,
      lastName,
      joiningdate: '',
      areaId: '1',
    };
  };

  // static setToken = (token) => {
  //   localStorage.setItem('token', token);
  // };

  static sanitizeGoogleUserData = ({
    userData = EMPTY_OBJECT,
  }) => {
    const {
      id: userID = EMPTY_STRING,
      name: userName = EMPTY_STRING,
    } = userData || EMPTY_OBJECT;
    return {
      ...userData,
      userID,
      userName,
      phone_number: EMPTY_STRING,
      joiningDate: EMPTY_STRING,
      areaId: EMPTY_STRING,
    };
  };
}

export default LoginSignupUtils;
