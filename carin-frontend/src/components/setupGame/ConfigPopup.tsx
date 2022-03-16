import './ConfigPopup.css';

type configPopupProps = {
  name: string;
  value: string;
  handleChange: (event: any) => void;
  handleClose: () => void;
  handleReset: () => void;
};

function ConfigPopup({ name, value, handleChange, handleReset, handleClose }: configPopupProps) {
  return (
    <div className="config-popup-box config-text-box">
      <div className="config-container  flex h-screen w-screen bg-neutral-900/50 z-50  py-20 textboxBackground">
        <div className="">
          <div className="flex flex-col pt-5 pb-10 ">
            <div className="center text-2xl font-bold">{name}</div>
            <form onSubmit={handleChange} className="center py-6">
              <textarea
                onChange={handleChange}
                value={value}
                rows={15}
                cols={80}
                className="fixedtextarea focus:ring ring-pink-500 focus:border-rose-700 
      focus:outline-none px-4 py-4 border-dashed border-4 border-rose-500 rounded-3xl"
              />
            </form>
            <div className="center">
              <div className="flex flex-row justify-center">
                <div className="px-2">
                  <button
                    type="submit"
                    className="bg-green-500 hover:bg-green-700 text-white 
        font-bold py-2 px-8 rounded-full text-black text-lg"
                    onClick={handleClose}>
                    save
                  </button>
                </div>

                <div className="px-2">
                  <button
                    className="bg-red-500 hover:bg-red-700 text-white 
        font-bold py-2 px-8 rounded-full text-black text-lg"
                    onClick={handleReset}>
                    Reset
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ConfigPopup;
