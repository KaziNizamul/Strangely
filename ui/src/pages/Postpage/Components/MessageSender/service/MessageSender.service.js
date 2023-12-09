import axios from 'axios';
/* service */
import {
  postService,
} from '../../../../../services/urls';
import { chatDMApi } from '../../../../Chat/service/Chat.service';
/* constant */
import {
  EMPTY_OBJECT,
  EMPTY_STRING,
} from '../../../../../resources/shared/global.constant';

export const createPost = payload => axios.post(postService.createPost, payload);

export const createGroupAsPerPost = ({
  descriptionSummary = EMPTY_STRING,
  userData = EMPTY_OBJECT,
}) => {
  const payload = {
    title: descriptionSummary,
  };
  return chatDMApi({
    payload,
    userData,
  });
};
