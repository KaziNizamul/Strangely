import React, {
  useEffect, useState, memo, useCallback,
} from 'react';
/* external imports */
import {
  connect,
} from 'react-redux';
import { useParams } from 'react-router-dom';
import PropTypes from 'prop-types';
import { message } from 'antd';
/* utils */
import _get from 'lodash/get';
/* internal components */
import MessageSender from '../MessageSender/MessageSender';
import Post from '../Post/Post';
/* hoc */
import withNavbar from '../../../../resources/shared/hoc/navbar';
import withSidebar from '../../../../resources/shared/hoc/sidebar';
/* actions */
import {
  updatePosts,
  resetPostData,
} from '../../data/PostPage.actions';
/* service */
import {
  getAllPostsByCategoryAndArea,
} from '../../service/PostPage.service';

/* styles */
import styles from './Feed.module.scss';
import {
  EMPTY_ARRAY,
  EMPTY_FUNCTION,
} from '../../../../resources/shared/global.constant';
import { TOASTER_MSG } from '../../constants/PostPage.constant';
import Utility from '../../../../utils/Utility';
import Spinner from '../Spinner';

function Feed({
  area_id = 1,
  allPosts = EMPTY_ARRAY,
  onFetchAllPosts = EMPTY_FUNCTION,
  onResetAllData = EMPTY_FUNCTION,
}) {
  useEffect(() => {
    fetchAllPosts();
    return () => {
      onResetAllData();
    };
  }, []);
  const { categoryId } = useParams();
  const [isLoading, setLoading] = useState(false);
  const [editPost, setEditPost] = useState(null);

  const fetchAllPosts = useCallback(async () => {
    setLoading(true);
    const payload = {
      post_category_id: categoryId || '',
      area_id: area_id || 1,
    };
    try {
      const data = await getAllPostsByCategoryAndArea(payload);
      onFetchAllPosts(data?.data || EMPTY_ARRAY);
    } catch (error) {
      message.error(TOASTER_MSG.FETCH_ALL_POST_FAILURE);
    } finally {
      setLoading(false);
    }
  }, []);

  const handleEditClick = (post) => {
    setEditPost(post);
  };

  return (
    <div className={styles.feed}>
      <div className={styles.postContainer}>
        {/* <CreatePost /> */}
        <MessageSender
          editPost={editPost}
          fetchAllPosts={fetchAllPosts}
          setLoading={setLoading}
          setEditPost={setEditPost}
        />
        <div className={styles.postCollection}>
          {isLoading
            ? <Spinner />
            : (
              <>
                {Utility.isEmptyArr(allPosts) && (
                  <span
                    style={{
                      display: 'flex',
                      justifyContent: 'center',
                    }}
                  >
                    No Posts to display 🫤
                  </span>
                )}
                {(allPosts || []).map(post => (
                  <Post
                    post={post}
                    editPost={editPost}
                    onEdit={handleEditClick}
                    fetchAllPosts={fetchAllPosts}
                  />
                ))}
              </>
            )}
        </div>
      </div>
    </div>
  );
}

Feed.propTypes = {
  allPosts: PropTypes.array,
  area_id: PropTypes.string,
  onFetchAllPosts: PropTypes.func,
  onResetAllData: PropTypes.func,
};

Feed.defaultProps = {
  area_id: 1,
  allPosts: EMPTY_ARRAY,
  onFetchAllPosts: EMPTY_FUNCTION,
  onResetAllData: EMPTY_FUNCTION,
};

const mapStateToProps = ({ postReducer, loginSignupReducer }) => ({
  area_id: _get(loginSignupReducer, 'area_id'),
  allPosts: _get(postReducer, 'allPosts'),
});

const mapDispatchToProps = dispatch => ({
  onFetchAllPosts: payload => dispatch(updatePosts(payload)),
  onResetAllData: () => dispatch(resetPostData()),
});

export default connect(
  mapStateToProps,
  mapDispatchToProps,
)(withNavbar((withSidebar(memo(Feed)))));
