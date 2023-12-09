export const ACTION_TYPES = {
  SET_LOADING: 'LOGIN_SIGNUP_SET_LOADING',
  SIGN_IN: 'LOGIN_SIGNUP_CHECKIN_TYPE_SIGNIN',
  SIGN_UP: 'LOGIN_SIGNUP_CHECKIN_TYPE_SIGNUP',
  UPDATE_FIELDS: 'LOGIN_SIGNUP_UPDATE_FIELDS',
  CHECKIN_TYPE: 'LOGIN_SIGNUP_CHEKCIN_TYPE',
  UPDATE_USER_DATA: 'LOGIN_SIGNUP_UPDATE_USER_DATA',
  RESET_ALL_DATA: 'LOGIN_SIGNUP_RESET_ALL_DATA',
  UPDATE_AREA_ID: 'LOGIN_SIGNUP_UPDATE_AREA_ID',
};

export const STRING_CONSTANTS = {
  SIGN_IN: 'signIn',
  SIGN_UP: 'signUp',
  STATUS_OK: 200,
};

export const TOASTER_MSG = {
  REGISTER_SUCCESS: 'User Registered Successfully.',
  REGISTER_FAILED: 'Registration failed',
  LOGIN_FAILURE: 'Error occured while logging in.',
  FAILED_WHILE_FETCHING_USER_DATA: 'Failed to fetch user data',
  LOGIN_FAILED: 'Login failed !',
  PASS_RESET_EMAIL_SENT: 'Password has been reset successfully',
  PASS_RESET_FAILED: 'Password reset failed. Please try again.',
  IF_USER_EXISTS_WILL_SENT_LINK: 'If the user exists, we will send you the reset link',
  ENTER_VALID_USERNAME: 'Please enter a valid username.',
  LOGGED_OUT_SUCCESSFULLY: 'Logged out successfully',
  LOGGED_OUT_FAILED: 'Error while logging out',
};
