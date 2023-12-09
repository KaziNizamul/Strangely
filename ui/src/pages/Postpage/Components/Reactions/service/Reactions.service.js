/* constant */
import {
  EMPTY_FUNCTION,
  EMPTY_STRING,
} from '../../../../../resources/shared/global.constant';
import { REACTION_TYPE } from '../constant/Reaction.constant';
/* service */
import {
  addLike,
  addDislike,
  addLove,
  removeLike,
  removeDislike,
  removeLove,
} from '../../Post/service/Post.service';

export const getReactionApi = ({
  type = EMPTY_STRING,
}) => {
  let reactApi = EMPTY_FUNCTION;
  switch (type) {
    case REACTION_TYPE.LIKE: {
      reactApi = handleLike;
      break;
    }
    case REACTION_TYPE.LOVE: {
      reactApi = handleLove;
      break;
    }
    case REACTION_TYPE.DISLIKE: {
      reactApi = handleDislike;
      break;
    }
    default: {
      return reactApi;
    }
  }
  return reactApi;
};

export const handleLike = async ({
  postId,
  likeCount,
  dislikeCount,
  fetchAllPosts,
}) => {
  try {
    if (dislikeCount) {
      await removeDislike(postId);
    }

    if (!likeCount) {
      await addLike(postId);
    } else {
      await removeLike(postId);
    }
  } catch (error) {
    console.error('Error handling like:', error);
  } finally {
    await fetchAllPosts();
  }
};

export const handleLove = async ({
  postId,
  loveCount,
  dislikeCount,
  fetchAllPosts,
}) => {
  try {
    if (dislikeCount) {
      await removeDislike(postId);
    }
    if (!loveCount) {
      await addLove(postId);
    } else {
      await removeLove(postId);
    }
  } catch (error) {
    console.error('Error handling love:', error);
  } finally {
    await fetchAllPosts();
  }
};

export const handleDislike = async ({
  postId,
  likeCount,
  dislikeCount,
  loveCount,
  fetchAllPosts,
}) => {
  try {
    if (likeCount) {
      await removeLike(postId);
    }

    if (loveCount) {
      await removeLove(postId);
    }

    if (!dislikeCount) {
      await addDislike(postId);
    } else {
      await removeDislike(postId);
    }
  } catch (error) {
    console.error('Error handling dislike:', error);
  }
  await fetchAllPosts();
};
