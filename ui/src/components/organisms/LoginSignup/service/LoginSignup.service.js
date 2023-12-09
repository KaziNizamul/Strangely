// import { http } from '@http';
import axios from 'axios';
/* service */
import {
  loginSignupService,
  homePageService,
} from '../../../../services/urls';
/* constants */
import {
  STRING_CONSTANTS,
  TOASTER_MSG,
} from '../constants/LoginSignup.constant';
/* actions */
import { updateUserData } from '../data/LoginSingup.actions';
/* utility */
import LoginSignupUtils from '../utils/LoginSignup.utils';

export const userRegistration = payload => axios.post(loginSignupService.userRegistration, payload);
export const userLogin = payload => axios.post(loginSignupService.userLogin, payload);
export const forgetPass = payload => axios.post(loginSignupService.forgetPass, payload);
export const sendUserNameResetLink = payload => axios.post(loginSignupService.sendUserNameResetLink, payload);
export const logoutUser = payload => axios.post(loginSignupService.logoutUser, payload);

export const fetchUserData = access_token => async (dispatch) => {
  const response = await axios.get(homePageService.userDataFromGoogleApi, {
    headers: {
      Authorization: `Bearer ${access_token}`,
    },
  });

  if (response.status === STRING_CONSTANTS.STATUS_OK) {
    const sanitizedGoogleInfoData = LoginSignupUtils.sanitizeGoogleUserData({
      userData: response?.data,
    });
    dispatch(updateUserData(sanitizedGoogleInfoData));
    return sanitizedGoogleInfoData;
  }

  throw new Error(TOASTER_MSG.FAILED_WHILE_FETCHING_USER_DATA);
};
