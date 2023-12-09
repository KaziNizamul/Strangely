/* eslint-disable jsx-a11y/control-has-associated-label */
import React, { memo } from 'react';
/* external imports */
import { PropTypes } from 'prop-types';
import moment from 'moment';

import {
  Menu,
  Dropdown,
  message,
} from 'antd';
import { useSelector } from 'react-redux';
import {
  EllipsisOutlined,
} from '@ant-design/icons';
/* styles */
import styles from './Post.module.scss';
import profilePic from '../../../../resources/assets/profileIcon.png';
/* utils */
import {
  EMPTY_ARRAY,
  EMPTY_FUNCTION,
  EMPTY_OBJECT,
  EMPTY_STRING,
} from '../../../../resources/shared/global.constant';
/* internal component */
import ImageComponent from '../ImageContainer';
import Reactions from '../Reactions';
/* constant */
import { TOASTER_MSG } from './constants/Post.constant';
/* service */
import {
  deletePost,
} from './service/Post.service';

function Post({
  post = EMPTY_OBJECT,
  onEdit = EMPTY_FUNCTION,
  fetchAllPosts = EMPTY_FUNCTION,
}) {
  const userData = useSelector(state => state.loginSignupReducer.userData);
  const {
    username = EMPTY_STRING,
    description = EMPTY_STRING,
    images = EMPTY_ARRAY,
    postDate = EMPTY_STRING,
    postId = EMPTY_STRING,
  } = post || EMPTY_OBJECT;

  const isUserPost = userData?.userName === username;

  const handleDelete = () => {
    deletePost(postId)
      .then(() => {
        message.success(TOASTER_MSG.SUCCEED_TO_DELETE_POST);
        fetchAllPosts();
      })
      .catch(() => {
        message.error(TOASTER_MSG.FAILED_TO_DELETE_POST);
      });
  };

  const menu = (
    <Menu>
      <Menu.Item
        key="0"
        onClick={() => onEdit({
          postId,
          photo: images[0],
          message: description,
        })}
      >
        Edit
      </Menu.Item>
      <Menu.Item
        key="1"
        onClick={handleDelete}
      >
        Delete
      </Menu.Item>
    </Menu>
  );

  return (
    <div className={styles.post}>
      {/* Header */}
      <div className={styles.postHeaderContainer}>
        <img
          src={profilePic}
          alt={`${username}'s profile`}
          className={styles.postProfilePic}
        />
        <div className={styles.userNameAndKebabContainer}>
          <div className={styles.postHeadeInfo}>
            <span className={styles.userName}>
              {username}
            </span>
            <span
              className={styles.postTimestamp}
            >
              {moment(postDate * 1000).format('DD MMM YYYY')}
            </span>
          </div>
          {isUserPost && (
            <Dropdown
              overlay={menu}
              trigger={['click']}
            >
              <div className={styles.dropDownMenu}>
                <a
                  role="button"
                  tabIndex="0"
                  className="ant-dropdown-link"
                  onClick={e => e.preventDefault()}
                >
                  <EllipsisOutlined />
                </a>
              </div>
            </Dropdown>
          )}
        </div>
      </div>
      {/* Body  */}
      <div className={styles.postBodyContainer}>
        <p>{description || EMPTY_STRING}</p>
        <ImageComponent
          base64={images[0]}
        />
      </div>
      {/* footer */}
      <div
        className={styles.postFooter}
      >
        <Reactions
          post={post}
          fetchAllPosts={fetchAllPosts}
        />
      </div>
    </div>
  );
}

Post.propTypes = {
  post: PropTypes.object.isRequired,
  onEdit: PropTypes.func.isRequired,
  fetchAllPosts: PropTypes.func.isRequired,
};

export default memo(Post);
