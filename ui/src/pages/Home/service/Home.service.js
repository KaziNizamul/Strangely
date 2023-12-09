import axios from 'axios';
/* service */
import {
  homeService,
} from '../../../services/urls';

export const getAreaId = payload => axios.post(homeService.getAreadId, payload);
export const postNewCategory = payload => axios.post(homeService.postNewCategory, payload);
export const getAllCategory = () => axios.get(homeService.getAllCategory);
