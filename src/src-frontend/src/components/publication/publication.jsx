import React from 'react';
import { Link } from 'react-router-dom';
//import userService from '../../service/user/userService';
import { CommentIcon, LikeIcon, DefaultProfileImg } from '../../assest/icons/components/publicationIcon';
import './style.scss'
import example from '../../assest/examples/pexels-anush-gorak-1431282.jpg';
import mockData from '../../data/mocker';

const PublicationComponent = () => {
    const participants = ['@user1', '@user2', '@user3', '@user4', '@user5'];

    return (
        <div className='publication-container'>
            {mockData.map((data, index) => (
                <div key={index} className='publication'>
                    <div className='publication-header'>
                        <DefaultProfileImg className='icon' />
                        <span id='title'>{data.username}</span>
                    </div>
                    <div className="img-container">
                        <img src={example} alt="" />
                    </div>
                    <div className='options-container'>
                        <div><LikeIcon className='icon' /></div>
                        <div><CommentIcon className='icon' /></div>
                    </div>
                    <div className='comment-container'>
                        <span id='title'>{data.username}</span>
                        <span>{`Participando en un evento de ${data.type.toLowerCase()}.`}</span>
                        <ul>
                            <li><strong>Type:</strong> <span>{data.type}</span></li>
                            <li><strong>Duration:</strong> <span>{data.duration}</span></li>
                            <li><strong>Place:</strong> <span>{data.place}</span></li>
                            <li><strong>Participants:</strong> <span>{data.participants.map(participant => (
                                <Link key={participant} to={`/${participant.substring(1)}`}>{participant}</Link>
                            )).reduce((prev, curr) => [prev, ', ', curr])}</span></li>
                        </ul>
                    </div>
                </div>
            ))}
        </div>
    );
};

export default PublicationComponent;