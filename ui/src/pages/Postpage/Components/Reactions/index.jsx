import React, { useEffect, useState } from 'react';
/* external imports */
import { PropTypes } from 'prop-types';
import {
  useSelector,
} from 'react-redux';
import {
  useNavigate,
} from 'react-router-dom';
import {
  LikeOutlined,
  DislikeOutlined,
  HeartOutlined,
} from '@ant-design/icons';
/* styles */
import styles from './Reactions.module.scss';
/* constants */
import {
  EMPTY_FUNCTION,
  EMPTY_OBJECT,
  EMPTY_STRING,
} from '../../../../resources/shared/global.constant';
import {
  REACTION_TYPE,
} from './constant/Reaction.constant';
/* service */
import {
  getReactionApi,
} from './service/Reactions.service';
import { createDirectMsg } from '../../../Chat/service/Chat.service';

function Reaction({
  post = EMPTY_OBJECT,
  fetchAllPosts = EMPTY_FUNCTION,
}) {
  const {
    postId = EMPTY_STRING,
    likeCount = EMPTY_STRING,
    dislikeCount = EMPTY_STRING,
    loveCount = EMPTY_STRING,
    username = EMPTY_STRING,
  } = post || EMPTY_OBJECT;

  const navigate = useNavigate();
  const userData = useSelector(state => state.loginSignupReducer.userData);
  const [liked, setLiked] = useState(false);
  const [loved, setLoved] = useState(false);
  const [disliked, setDisliked] = useState(false);

  useEffect(() => {
    setLiked(likeCount > 0);
    setDisliked(dislikeCount > 0);
    setLoved(loveCount > 0);
  }, []);

  const onHandleReaction = ({
    type = EMPTY_STRING,
  }) => {
    const reactionApi = getReactionApi({
      type,
    });
    reactionApi({
      postId,
      likeCount,
      loveCount,
      dislikeCount,
      fetchAllPosts,
    });
  };

  const handleChatClick = async () => {
    const payload = {
      is_direct_chat: true,
      usernames: [username],
    };
    await createDirectMsg({
      payload,
      userData,
    });
    navigate('/chat');
  };

  return (
    <div className={styles.postOptions}>
      <div
        role="button"
        tabIndex="0"
        className={styles.postOption}
        onClick={() => onHandleReaction({
          type: REACTION_TYPE.LIKE,
        })}
      >
        <LikeOutlined
          style={{
            color: liked
              ? 'green'
              : 'gray',
            paddingRight: '1rem',
          }}
        />
        <span> {likeCount} Like</span>
      </div>
      <div
        role="button"
        tabIndex="0"
        className={styles.postOption}
        onClick={() => onHandleReaction({
          type: REACTION_TYPE.DISLIKE,
        })}
      >
        <DislikeOutlined
          style={{
            color: disliked
              ? 'blue'
              : 'gray',
            paddingRight: '1rem',
          }}
        />
        <span>{dislikeCount} Dislike </span>
      </div>
      <div
        role="button"
        tabIndex="0"
        className={styles.postOption}
        onClick={() => onHandleReaction({
          type: REACTION_TYPE.LOVE,
        })}
      >
        <HeartOutlined
          style={{
            color: loved
              ? 'red'
              : 'gray',
            paddingRight: '1rem',
          }}
        />
        <span>{loveCount} Love</span>
      </div>
      {((username || '') !== (userData?.userName)) && (
        <div
          role="button"
          tabIndex="0"
          className={styles.postOption}
          onClick={handleChatClick}
        >
          <span>Chat 💬 </span>
        </div>
      )}
    </div>
  );
}

Reaction.propTypes = {
  post: PropTypes.any.isRequired,
  fetchAllPosts: PropTypes.func.isRequired,
};

export default Reaction;
