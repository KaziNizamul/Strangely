import axios from 'axios';
/* service */
import {
  postService,
} from '../../../../../services/urls';

export const deletePost = id => axios.delete(postService.deletePost.replace('id', id));
export const editPosts = (payload, id) => axios.put(postService.editPost.replace('id', id), payload);
// reactions
export const addLike = id => axios.post(postService.likePost.replace('id', id));
export const addDislike = id => axios.post(postService.disLikePost.replace('id', id));
export const addLove = id => axios.post(postService.lovePost.replace('id', id));
export const removeLike = id => axios.delete(postService.removeLikePost.replace('id', id));
export const removeDislike = id => axios.delete(postService.removeDisLikePost.replace('id', id));
export const removeLove = id => axios.delete(postService.removeLovePost.replace('id', id));
