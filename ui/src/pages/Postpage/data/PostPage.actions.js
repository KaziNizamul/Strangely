import { EMPTY_STRING } from '../../../resources/shared/global.constant';
import { ACTION_TYPES } from '../constants/PostPage.constant';

export const updatePosts = payload => ({
  type: ACTION_TYPES.UPDATE_POST_DATA,
  payload,
});

export const resetPostData = () => ({
  type: ACTION_TYPES.RESET_ALL_DATA,
  payload: EMPTY_STRING,
});
