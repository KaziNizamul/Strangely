import axios from 'axios';
import { baseURL } from './urls';

const token = localStorage.getItem('token');

export const axiosObject = axios.create({
  baseURL,
  headers: {
    common: {
      Authorization: `Bearer ${token}`,
    },
  },
});

export const http = {
  post: axiosObject.post,
  get: axiosObject.get,
  put: axiosObject.put,
  patch: axiosObject.patch,
  delete: axiosObject.delete,
};
