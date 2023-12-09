/* eslint-disable default-param-last */
import {
  EMPTY_OBJECT,
  EMPTY_STRING,
} from '../../../../resources/shared/global.constant';
import { ACTION_TYPES } from '../constants/LoginSignup.constant';

const initialState = {
  isLoading: false,
  name: '',
  email: '',
  password: '',
  phoneNumber: '',
  checkinType: '',
  userData: '',
  area_id: 1,
};

const loginSignupReducer = (state = initialState, { type, payload }) => {
  const {
    name = EMPTY_STRING,
    email = EMPTY_STRING,
    password = EMPTY_STRING,
    value = EMPTY_STRING,
    fieldName = EMPTY_STRING,
    checkinType = EMPTY_STRING,
  } = payload || EMPTY_OBJECT;
  switch (type) {
    case ACTION_TYPES.SET_LOADING:
      return {
        ...state,
        isLoading: payload,
      };
    case ACTION_TYPES.UPDATE_FIELDS:
      return {
        ...state,
        [fieldName]: value,
      };
    case ACTION_TYPES.SIGN_IN:
      return {
        ...state,
        email,
        password,
      };
    case ACTION_TYPES.SIGN_UP:
      return {
        ...state,
        name,
        email,
        password,
      };
    case ACTION_TYPES.CHECKIN_TYPE:
      return {
        ...state,
        checkinType,
      };
    case ACTION_TYPES.UPDATE_USER_DATA:
      return {
        ...state,
        userData: payload,
      };
    case ACTION_TYPES.UPDATE_AREA_ID:
      return {
        ...state,
        area_id: payload,
      };
    case ACTION_TYPES.RESET_ALL_DATA:
      return {
        ...state,
        ...initialState,
      };
    default:
      return state;
  }
};

export default loginSignupReducer;
