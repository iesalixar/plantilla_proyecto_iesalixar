import './style.scss';
import { LogingButton, RegisterButton } from '../buttons/buttons';
import { useLocation } from 'react-router-dom';
import SvgComponent from '../logo/logofull.jsx';
import Logoicon from '../logo/logoIcon.jsx';
import Logotext from '../logo/logotext.jsx';
import { Link } from 'react-router-dom';


const DefaultHeader = () => {
    return (
        <div className="header">
            <div className='logo-container'>
                <Link to="/home"><Logotext className="logo-svg"></Logotext></Link>
            </div>

        </div>

    );
};
const RegisterHeader = () => {
    const location = useLocation();
    const pathname = location.pathname;
    return (
        <div className="header">
            <div className='logo-container'>
                <Link to="/home"> <SvgComponent></SvgComponent></Link>
            </div>
            <div className='btn-container'>
                {pathname === '/register' ? <LogingButton /> : <RegisterButton />}
            </div>
        </div>

    );
};

const IconHeader = () => {
    return (
        <div className="header">
            <div className='logo-container'>
                <Link to="/home"><Logoicon></Logoicon></Link>
            </div>
        </div>

    );
};
export { DefaultHeader, RegisterHeader, IconHeader };