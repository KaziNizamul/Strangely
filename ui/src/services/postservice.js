// src/services/postService.js (or src/postService.js)
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api'; // Replace with your actual API base URL

export const addPost = async postDTO => axios.post(`${API_BASE_URL}/posts`, postDTO);

export const updatePost = async (postId, postDTO) => axios.put(`${API_BASE_URL}/posts/${postId}`, postDTO);

export const deletePost = async postId => axios.delete(`${API_BASE_URL}/posts/${postId}`);
