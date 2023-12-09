/* eslint-disable default-param-last */
import {
// EMPTY_ARRAY,
// EMPTY_OBJECT,
} from '../../../resources/shared/global.constant';
import { ACTION_TYPES } from '../constants/PostPage.constant';

const initialState = {
  allPosts: [],
};

const postPageReducer = (state = initialState, { type, payload }) => {
  // const {
  //   allPosts = EMPTY_ARRAY,
  // } = payload || EMPTY_OBJECT;
  switch (type) {
    case ACTION_TYPES.UPDATE_POST_DATA:
      return {
        ...state,
        allPosts: payload,
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

export default postPageReducer;
