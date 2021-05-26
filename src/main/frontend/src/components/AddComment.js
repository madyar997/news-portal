import React, { useState } from 'react';

function AddComment(props) {
    const { handleCommentSubmit } = props;
    const [comment, setComment] = useState('');
    return (
        <div>
            <div className='card mt-4 mb-3'>
                <div className='card header'>
                    <string>Comments</string>
                </div>
                <div className='card-body'>
                    <textarea name='comment' className='form-control' placeholder='Add a new comment'
                        onChange={event =>setComment(event.target.value)} value={comment}></textarea>
                </div>
            </div>
            <div>
                <button className='btn btn-primary mr-3' onClick={event => {
                    handleCommentSubmit(comment);
                    setComment('');
                }}>Comment</button>
                <button className='btn btn-warning'>Close issue</button>
            </div>
        </div>
    );

}




export default AddComment;