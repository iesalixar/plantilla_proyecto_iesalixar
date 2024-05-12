import React from 'react';

function Footer() {
    return (
        <footer className="py-5 bg-none">
            <div className="container">
                <div className="row">
                    <div className="col-6 col-md-2 mb-3">
                        <h5>Section</h5>
                        <ul className="nav flex-column">
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Home</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Features</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Pricing</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">FAQs</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">About</a></li>
                        </ul>
                    </div>

                    <div className="col-6 col-md-2 mb-3">
                        <h5>Section</h5>
                        <ul className="nav flex-column">
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Home</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Features</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Pricing</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">FAQs</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">About</a></li>
                        </ul>
                    </div>

                    <div className="col-6 col-md-2 mb-3">
                        <h5>Section</h5>
                        <ul className="nav flex-column">
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Home</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Features</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">Pricing</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">FAQs</a></li>
                            <li className="nav-item mb-2"><a href="#" className="nav-link text-muted">About</a></li>
                        </ul>
                    </div>

                    <div className="col-md-5 offset-md-1 mb-3">
                        <form>
                            <h5>Subscribe to our newsletter</h5>
                            <p>Monthly digest of what's new and exciting from us.</p>
                            <div className="d-flex flex-column flex-sm-row w-100 gap-2">
                                <label htmlFor="newsletter1" className="visually-hidden">Email address</label>
                                <input id="newsletter1" type="text" className="form-control" placeholder="Email address" />
                                <button className="btn primary-custom-btn" type="button">Subscribe</button>
                            </div>
                        </form>
                    </div>
                </div>

                <div className="d-flex flex-column flex-sm-row justify-content-between py-4 my-4 border-top">
                    <p className="text-muted">© 2024 Company, Inc. All rights reserved.</p>
                    <ul className="list-unstyled d-flex">
                        <li className="ms-3"><a className="link-dark" href="#"><svg className="bi" width="24" height="24"><use xlinkHref="#twitter"></use></svg></a></li>
                        <li className="ms-3"><a className="link-dark" href="#"><svg className="bi" width="24" height="24"><use xlinkHref="#instagram"></use></svg></a></li>
                        <li className="ms-3"><a className="link-dark" href="#"><svg className="bi" width="24" height="24"><use xlinkHref="#facebook"></use></svg></a></li>
                    </ul>
                </div>
            </div>
        </footer>
    );
}
export default Footer;