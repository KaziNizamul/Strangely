/* eslint-disable react/prop-types */
/* eslint-disable jsx-a11y/control-has-associated-label */
import React, { useState, useRef, useEffect } from 'react';
/* external imports */
import SendIcon from '@mui/icons-material/Send';
import {
  connect,
} from 'react-redux';
import PropTypes from 'prop-types';
import _get from 'lodash/get';
import { message } from 'antd';
import {
  PictureOutlined,
} from '@ant-design/icons';
import {
  useParams,
} from 'react-router-dom';
import TextareaAutosize from 'react-textarea-autosize';
/* styles */
import styles from './MessageSender.module.scss';
/* constant */
import {
  EMPTY_FUNCTION,
  EMPTY_OBJECT,
  EMPTY_STRING,
} from '../../../../resources/shared/global.constant';
/* service */
import {
  createGroupAsPerPost,
  createPost,
} from './service/MessageSender.service';
import {
  editPosts,
} from '../Post/service/Post.service';
import {
} from '../../service/PostPage.service';
import { updatePosts } from '../../data/PostPage.actions';
import { TOASTER_MSG } from '../../constants/PostPage.constant';
/* utils */
import MessageSenderUtils from './utils/MessageSender.util';
import Utility from '../../../../utils/Utility';

function MessageSender({
  userData = EMPTY_OBJECT,
  area_id = 1,
  fetchAllPosts = EMPTY_FUNCTION,
  setLoading = EMPTY_FUNCTION,
  editPost = EMPTY_OBJECT,
  setEditPost = EMPTY_FUNCTION,
}) {
  const [postContent, setPostContent] = useState('');
  const [image, setImage] = useState(null);

  useEffect(() => {
    if (editPost) {
      setPostContent(editPost.message);
      setImage(editPost.photo);
      setImagePreview(editPost.photo);
    } else {
      setPostContent('');
      setImage('');
      setImagePreview('');
    }
  }, [editPost]);

  const [imagePreview, setImagePreview] = useState(null);
  const [fileName, setFileName] = useState(null);
  const fileInput = useRef(null);

  const {
    userName = EMPTY_STRING,
  } = userData || EMPTY_OBJECT;
  const { categoryId } = useParams();

  const createAutoGroup = async () => {
    const descriptionSummary = MessageSenderUtils.getSummaryText({
      description: postContent || EMPTY_STRING,
    });
    try {
      await createGroupAsPerPost({
        descriptionSummary,
        userData,
      });
    } catch (error) {
      message.error(TOASTER_MSG.FAILED_TO_CREATE_GROUP);
    }
  };

  const handleCreatePost = async (payload) => {
    setLoading(true);
    try {
      const response = await createPost(payload);
      message.success(response?.data?.status || EMPTY_STRING);
      await fetchAllPosts();
      await createAutoGroup();
    } catch (error) {
      message.error(TOASTER_MSG.CREATE_POST_FAILURE);
    } finally {
      setLoading(false);
      setEditPost(null);
    }
  };

  const handleEditPost = async (payload) => {
    setLoading(true);
    editPosts(payload, editPost?.postId)
      .then(() => {
        fetchAllPosts();
      })
      .catch(() => {
        message.error(TOASTER_MSG.EDIT_POST_FAILURE);
      })
      .finally(() => {
        setEditPost(null);
        setLoading(false);
      });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    let base64Image = null;
    if (image && !Utility.isObjectDefined(editPost)) {
      base64Image = await MessageSenderUtils.toBase64(image);
    } else {
      base64Image = image;
    }

    const payload = {
      username: userName || EMPTY_STRING,
      description: postContent || EMPTY_STRING,
      images: base64Image ? [base64Image] : [],
      categoryId: Number(categoryId),
      area_id: area_id || 1,
      likeCount: 0,
      loveCount: 0,
      dislikeCount: 0,
    };

    if (editPost && Utility.isObjectDefined(editPost)) {
      handleEditPost({
        username: userName || EMPTY_STRING,
        description: postContent || EMPTY_STRING,
        categoryId: Number(categoryId),
        area_id: area_id || 1,
      });
    } else {
      handleCreatePost(payload);
    }

    setPostContent('');
    setImage(null);
    setImagePreview(null);
    setFileName(null);
  };

  const handleFileChange = (e) => {
    if (e.target.files[0]) {
      const url = URL.createObjectURL(e.target.files[0]);
      setImagePreview(url);
      setFileName(e.target.files[0].name);
      setImage(e.target.files[0]);
    }
  };

  const handleFileClick = () => {
    fileInput.current.click();
  };

  return (
    <div className={styles.messageSender}>
      <div className={styles.messageSenderTop}>
        <form>
          {/* Post Content */}
          <TextareaAutosize
            className={styles.messageSenderInput}
            placeholder={`What's on your mind, ${userName || ''}?`}
            value={postContent}
            onChange={e => setPostContent(e.target.value)}
          />
          {/* Image Preview */}
          <div
            className={styles.imageDisplayContainer}
          >
            {imagePreview && (
              <div
                className={styles.fileContainer}
              >
                <img
                  src={imagePreview}
                  title={fileName}
                  alt="preview"
                  className={styles.previewImage}
                  height={70}
                  width={70}
                />
                <span
                  title={fileName}
                  className={styles.fileName}
                >
                  {fileName}
                </span>
              </div>
            )}
          </div>
          {/* invisible file uploader */}
          <input
            type="file"
            ref={fileInput}
            style={{ display: 'none' }}
            onChange={handleFileChange}
          />
          <div
            tabIndex="0"
            role="button"
            className={styles.messageSenderFileSelector}
          >
            {/* Media Related */}
            <div
              className={styles.fileSelector}
              onClick={handleFileClick}
              tabIndex="0"
              role="button"
            >
              <div
                className={styles.fileSelectorLogo}
                onClick={handleFileClick}
                tabIndex="0"
                role="button"
              >
                <PictureOutlined />
              </div>
              <span
                className={styles.fileSelectorText}
              >
                Media
              </span>
            </div>
            {/* sender icon */}
            <div
              tabIndex="0"
              role="button"
              onClick={handleSubmit}
              className={styles.senderBtn}
              title="Send Post"
            >
              <SendIcon />
            </div>
          </div>
        </form>
      </div>
    </div>
  );
}

MessageSender.propTypes = {
  userData: PropTypes.any,
  area_id: PropTypes.string,
  editPost: PropTypes.object,
  setLoading: PropTypes.func,
  fetchAllPosts: PropTypes.func,
};

MessageSender.defaultProps = {
  userData: EMPTY_OBJECT,
  area_id: 1,
  editPost: EMPTY_OBJECT,
  setLoading: EMPTY_FUNCTION,
  fetchAllPosts: EMPTY_FUNCTION,
};

const mapStateToProps = ({ loginSignupReducer }) => ({
  userData: _get(loginSignupReducer, 'userData'),
  area_id: _get(loginSignupReducer, 'area_id'),
});

const mapDispatchToProps = dispatch => ({
  onFetchAllPosts: payload => dispatch(updatePosts(payload)),
});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(MessageSender);
