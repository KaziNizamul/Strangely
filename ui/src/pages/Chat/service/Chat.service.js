import axios from 'axios';
import { message } from 'antd';
/* service */
import {
  chatService,
} from '../../../services/urls';
/* constant */
import {
  EMPTY_OBJECT,
  EMPTY_STRING,
} from '../../../resources/shared/global.constant';

export const getOrCreateMeAsUser = ({
  userData = EMPTY_OBJECT,
}) => axios.get(chatService.getMeAsUser, {
  headers: {
    'project-id': import.meta.env.VITE_CHAT_PROJECT_ID,
    'user-name': userData?.userName || EMPTY_STRING,
    'user-secret': userData?.phone_number || EMPTY_STRING,
  },
})
  .catch(() => {
    const formdata = new FormData();
    formdata.append('email', userData?.email);
    formdata.append('username', userData?.userName);
    formdata.append('secret', userData?.phone_number);
    axios.post(chatService.createMeAsUser, formdata, {
      headers: {
        'private-key': import.meta.env.VITE_CHAT_PVT_KEY,
      },
    });
  });

export const createDirectMsg = ({
  payload,
  userData,
}) => {
  const headers = {
    'project-id': import.meta.env.VITE_CHAT_PROJECT_ID,
    'user-name': userData?.userName || EMPTY_STRING,
    'user-secret': userData?.phone_number || EMPTY_STRING,
  };

  axios.put(chatService.createGroup, payload, {
    headers,
  })
    .catch((err) => {
      message.error(err?.message || '');
    });
};

export const chatDMApi = ({
  userData = EMPTY_OBJECT,
  payload,
}) => {
  const headers = {
    'project-id': import.meta.env.VITE_CHAT_PROJECT_ID,
    'user-name': userData?.userName || EMPTY_STRING,
    'user-secret': userData?.phone_number || EMPTY_STRING,
  };

  return axios.post(chatService.createGroup, payload, {
    headers,
  });
};

export const createGroupChat = ({
  title = '',
  userData = EMPTY_OBJECT,
}) => {
  const payload = {
    title,
  };
  const headers = {
    'project-id': import.meta.env.VITE_CHAT_PROJECT_ID,
    'user-name': userData?.userName || EMPTY_STRING,
    'user-secret': userData?.phone_number || EMPTY_STRING,
  };
  axios.post(chatService.createGroup, payload, {
    headers,
  });
};
