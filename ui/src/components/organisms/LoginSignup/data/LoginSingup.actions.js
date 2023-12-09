import { EMPTY_OBJECT } from '../../../../resources/shared/global.constant';
import { ACTION_TYPES } from '../constants/LoginSignup.constant';

export const signIn = payload => ({
  type: ACTION_TYPES.SIGN_IN,
  payload,
});

export const signUp = payload => ({
  type: ACTION_TYPES.SIGN_UP,
  payload,
});

export const updateFields = payload => ({
  type: ACTION_TYPES.UPDATE_FIELDS,
  payload,
});

export const updateCheckinType = payload => ({
  type: ACTION_TYPES.CHECKIN_TYPE,
  payload,
});

export const updateUserData = payload => ({
  type: ACTION_TYPES.UPDATE_USER_DATA,
  payload,
});

export const setLoading = payload => ({
  type: ACTION_TYPES.SET_LOADING,
  payload,
});

export const updateAreaId = payload => ({
  type: ACTION_TYPES.UPDATE_AREA_ID,
  payload,
});

export const resetAllData = () => ({
  type: ACTION_TYPES.RESET_ALL_DATA,
  payload: EMPTY_OBJECT,
});
