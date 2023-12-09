export const baseURL = 'http://172.17.1.123:8073';
export const chatEngineUrl = 'https://api.chatengine.io';

export const loginSignupService = {
  userRegistration: `${baseURL}/registerSS`,
  userLogin: `${baseURL}/loginSS`,
  forgetPass: `${baseURL}/resetpasswordSS`,
  sendUserNameResetLink: `${baseURL}/resetSS`,
  logoutUser: `${baseURL}/logoutSS`,
  updateProfile: `${baseURL}/strangely/update/`,
};

export const postService = {
  getAllPostsByCategoryAndArea: `${baseURL}/post/get_post_by_filter`,
  createPost: `${baseURL}/post/addpost`,
  deletePost: `${baseURL}/post/delete-post/id`,
  editPost: `${baseURL}/post/edit-post/id`,
  // reaction
  likePost: `${baseURL}/post/add-like/id`,
  removeLikePost: `${baseURL}/post/remove-like/id`,
  disLikePost: `${baseURL}/post/add-dislike/id`,
  removeDisLikePost: `${baseURL}/post/remove-dislike/id`,
  lovePost: `${baseURL}/post/add-love/id`,
  removeLovePost: `${baseURL}/post/remove-love/id`,
};

export const homeService = {
  getAreadId: `${baseURL}/getIdByLatLong`,
  postNewCategory: `${baseURL}/post-categories/add`,
  getAllCategory: `${baseURL}/post-categories/getAllCategories`,
};

export const chatService = {
  getMeAsUser: `${chatEngineUrl}/users/me/`,
  createMeAsUser: `${chatEngineUrl}/users/`,
  createGroup: `${chatEngineUrl}/chats/`,
};

export const homePageService = {
  userDataFromGoogleApi: 'https://www.googleapis.com/oauth2/v1/userinfo',
};
