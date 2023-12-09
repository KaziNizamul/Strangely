import axios from 'axios';
/* urls */
import {
  postService,
} from '../../../services/urls';

// urls
export const getAllPostsByCategoryAndArea = payload => axios.post(postService.getAllPostsByCategoryAndArea, payload);
