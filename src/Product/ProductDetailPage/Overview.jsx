import React from 'react'
import Placeholder from '/Images/Common/placeholder.png'

export default function Overview() {
  return (
    
    <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
    <div className="lg:col-span-2 space-y-8">
        {/* Primary Details */}
        <section>
            <h2 className="text-lg font-semibold text-seconday mb-4">Primary Details</h2>
            <div className="grid gap-4">
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Product name</div>
                    <div className='text-value'>Paracetmol</div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Product ID</div>
                    <div className='text-value'>456567</div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Model No</div>
                    <div className='text-value'>456567</div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Product Type</div>
                    <div className='text-value'>Consumable</div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Product category</div>
                    <div className='text-value'>Fever Medicine</div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Item Type</div>
                    <div className='text-value'>Medicine</div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Expiry Date</div>
                    <div className='text-value'>13/4/23</div>
                </div>
            </div>
        </section>

        {/* Supplier Details */}
        <section>
            <h2 className="text-lg font-semibold text-seconday mb-4">Supplier Details</h2>
            <div className="grid gap-4">
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Supplier name</div>
                    <div className='text-value'>John Doe</div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                    <div className="text-key font-medium">Contact Number</div>
                    <div className='text-value'>99999 88888</div>
                </div>
            </div>
        </section>

        {/* Stock Locations */}
        <section>
            <h2 className="text-lg font-semibold text-seconday mb-4">Sub Parts</h2>
            <div className="rounded-lg overflow-hidden">
                <div className="grid grid-cols-2 gap-4 p-4 font-medium bg-[#F0F1F3]">
                    <div className='text-tertiary font-semibold'>Parts Name</div>
                    <div className='text-tertiary font-semibold'>Parts in hand</div>
                </div>
                <div className="divide-y">
                    <div className="grid grid-cols-2 gap-4 p-4">
                        <div className='text-value'>Injection</div>
                        <div className="text-value">15</div>
                    </div>
                    <div className="grid grid-cols-2 gap-4 p-4">
                        <div className='text-value'>Tablets</div>
                        <div className="text-value">19</div>
                    </div>
                </div>
            </div>
        </section>
    </div>

    <div className="lg:col-span-1">
        {/* Product Image */}
        <div className="border-2 border-dashed rounded-lg p-4 mb-6">
            <div className="bg-gray-100 aspect-square rounded-lg">
                <img src={Placeholder} alt="Product" className="object-cover h-full w-full" />
            </div>
        </div>

        {/* Stock Information */}
        <div className="space-y-4">
            <div className="flex justify-between items-center">
                <span className="text-key font-medium">Opening Stock</span>
                <span className="text-value">40</span>
            </div>
            <div className="flex justify-between items-center">
                <span className="text-key font-medium">Remaining Stock</span>
                <span className="text-value">34</span>
            </div>
            <div className="flex justify-between items-center">
                <span className="text-key font-medium">On the way</span>
                <span className="text-value">15</span>
            </div>
            <div className="flex justify-between items-center">
                <span className="text-key font-medium">Threshold value</span>
                <span className="text-value">12</span>
            </div>
            <div className="flex justify-between items-center">
                <span className="text-key font-medium">Department</span>
                <span className="text-value">Medicines</span>
            </div>
            <div className="flex justify-between items-center">
                <span className="text-key font-medium">Store</span>
                <span className="text-value">Central</span>
            </div>
        </div>
    </div>
</div>
  )
}
