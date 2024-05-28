import React from 'react';
import { CommentIcon, LikeIcon } from '../icons/publicationIcon';
import './style.scss'
import example from '../../assest/examples/pexels-anush-gorak-1431282.jpg';

const PublicationComponent = () => {
    return (
        <div className="publication-container">
            <div className='publication'>
                <div className="img-container">
                    <img src="" alt="" />
                </div>
                <div className='options-container'>
                    <div className='icon'><CommentIcon /></div>
                    <div><LikeIcon className='icon' /></div>
                </div>
                <div className='comment-container'>
                    <span id='title'>Username</span><span>Corriendo la maratón.</span>
                </div>
            </div>
            <div className='publication'>
                <div className="img-container">
                    <img src="" alt="" />
                </div>
                <div className='options-container'>
                    <div className='icon'><CommentIcon /></div>
                    <div><LikeIcon className='icon' /></div>
                </div>
                <div className='comment-container'>
                    <span id='title'>Username</span><span>Corriendo la maratón.</span>
                </div>
            </div>

        </div >
    );

};

export default PublicationComponent;