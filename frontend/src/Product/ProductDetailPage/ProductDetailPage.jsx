import React, { useState } from 'react';
import Purchases from './Purchases';
import Overview from './Overview';
import Suppliers from './Suppliers';

function ProductDetailPage() {
    const [activeTab, setActiveTab] = useState('Overview');
    const [isEditing, setIsEditing] = useState(false);

    const renderContent = () => {
        switch (activeTab) {
            case 'Purchases':
                return <Purchases />;
            case 'Overview':
                return <Overview isEditing={isEditing} />;
            case 'Suppliers':
                return <Suppliers/>
            default:
                return (
                    <>
                        <p>No content</p>
                    </>
                );
        }
    };

    return (
        <>
            <div className="w-full p-12">
                <div className="p-6 w-full mx-auto bg-white rounded-lg">
                    <div className="flex justify-between items-center mb-6">
                        <h1 className="text-2xl font-semibold text-secondary">Paracetamol</h1>
                        <div className="flex gap-3">
                            {activeTab === 'Overview' && isEditing ? (
                                <>
                                    <button 
                                        onClick={() => {
                                            if (window.confirm('Are you sure you want to save these changes?')) {
                                                setIsEditing(false);
                                                // Add logic here to save the changes
                                            }
                                        }}
                                        className="flex items-center gap-2 px-4 py-2 border border-formBorder rounded-md text-tertiary hover:bg-gray-50"
                                    >
                                        <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M5 13l4 4L19 7" />
                                        </svg>
                                        Save
                                    </button>
                                    <button 
                                        onClick={() => {
                                            if (window.confirm('Are you sure you want to cancel? All changes will be lost.')) {
                                                setIsEditing(false);
                                                // Add logic here to revert any changes
                                            }
                                        }}
                                        className="flex items-center gap-2 px-4 py-2 border border-formBorder rounded-md text-tertiary hover:bg-gray-50"
                                    >
                                        <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                                        </svg>
                                        Cancel
                                    </button>
                                </>
                            ) : activeTab === 'Overview' && (
                                <button 
                                    onClick={() => setIsEditing(true)}
                                    className="flex items-center gap-2 px-4 py-2 border border-formBorder rounded-md text-tertiary hover:bg-gray-50"
                                >
                                    <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z" />
                                    </svg>
                                    Edit
                                </button>
                            )}
                            {activeTab === 'Purchases' && (
                                <button className="flex items-center gap-2 px-4 py-2 border border-formBorder rounded-md text-tertiary hover:bg-gray-50">
                                    Download
                                </button>
                            )}
                        </div>
                    </div>

                    {/* Navigation */}
                    <div className="border-b mb-6">
                        <nav className="flex gap-8">
                            {['Overview', 'Purchases', 'Suppliers',].map((tab) => (
                                <button
                                    key={tab}
                                    onClick={() => setActiveTab(tab)}
                                    className={`py-2 px-1 -mb-px ${
                                        tab === activeTab ? 'border-b-2 border-primary text-primary' : 'text-tertiary'
                                    }`}
                                >
                                    {tab}
                                </button>
                            ))}
                        </nav>
                    </div>

                    {renderContent()}
                </div>
            </div>
        </>
    );
}

export default ProductDetailPage;

