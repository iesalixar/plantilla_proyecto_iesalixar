import { Link } from 'react-router-dom';

const RegisterHeader = ({ leftContent, rightContent }) => {
    return (
        <div className="header-container">
            <div className='logo-container'>
                <Link to="/home">{leftContent}</Link>
            </div>
            <div className='btn-container'>
                {rightContent}
            </div>
        </div>

    );
};

const IconHeader = ({ leftContent, rightContent }) => {
    return (
        <div className="header-container">
            <div className='logo-container'>
                <Link to="/home">{leftContent}</Link>
            </div>
            <div className='option-container'>{rightContent}</div>
        </div>

    );
};
export { RegisterHeader, IconHeader };