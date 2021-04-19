import React from 'react';
import Select from 'react-select';

class CryptoTickerForm extends React.Component {
	constructor(props) {
		super(props);
		this.state = {name: '', geckoId:'', options: [] };
		this.handleChange = this.handleChange.bind(this);
		this.handleSubmit = this.handleSubmit.bind(this);
		this.fetchAllCoinsData = this.fetchAllCoinsData.bind(this);

	}
	
	handleChange(event) {  this.setState({id: event.value, name:event.label.substr(event.label.indexOf(' ')+1, event.label.length) }); }
	handleSubmit(event) {
		// save data
		const response = fetch("/api/coinGeckoTickers", {
		    method: "POST", 
		    headers: {
		      "Content-Type": "application/json"
		    },
		    body: JSON.stringify( {geckoId: this.state.id, name: this.state.name})
	    })
		.then(resp => resp.json())
		.then(
	      (result) => {
			this.props.updateData(result)
			},
	      (error) => {
			console.log(error);
	        this.setState({
	          isLoaded: true,
	          error
	        });
	      }
	    );

		// close modal
		document.getElementById('closeGeckoModal').click();

		// prevent page submit
		event.preventDefault();
  	}

	fetchData(page) {
		fetch("https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=250&page="+page)
		    .then(res => res.json())
		    .then(
		      (result) => {	
				console.log(result);
				
				//create options object for react-select dropdown
				let tmpOptions = [];
				result.map( (item, index) => tmpOptions.push({ value: item.id, label: '#'+item.market_cap_rank+' '+ item.name, market_cap_rank: item.market_cap_rank }));
				let tmpMergedOptions = tmpOptions.concat(this.state.options);
				tmpMergedOptions.sort(function sortByMarketCap(a, b) {
					  return a.market_cap_rank - b.market_cap_rank;
					}
				);
				
				this.setState({options: tmpMergedOptions});
				
		      },
		      // Note: it's important to handle errors here
		      // instead of a catch() block so that we don't swallow
		      // exceptions from actual bugs in components.
		      (error) => {
				console.log(error);
		        this.setState({
		          isLoaded: true,
		          error
		        });
		      }
		    )			
			
	}




	// get coin names from coingecko
	fetchAllCoinsData() {
		if (this.state.options.length == 0) {
			this.fetchData(1);
			
			for (let i = 2 ; i <= 1; i++) {
				setTimeout(this.fetchData(i), i*750);	
			}
		}
	}
	

	
	render() {

		return (
			<div >
				<button type="button" className="btn btn-primary" data-bs-toggle="modal" data-bs-target="#geckoModal" onClick={this.fetchAllCoinsData}>
					Add Coin
				</button>
				<form onSubmit={this.handleSubmit}>   
					<div className="modal fade" id="geckoModal" tabIndex="-1" aria-labelledby="geckoModalLabel" aria-hidden="true">
						<div className="modal-dialog">
					   		<div className="modal-content">
					      		<div className="modal-header">
					        		<h5 className="modal-title" id="geckoModalLabel" >Add coin</h5>
					        		<button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					      		</div>
					      		<div className="modal-body autocomplete">    
					          		Coin:
					          		<Select loading="true" onChange={this.handleChange} options={this.state.options} />        
			     				</div>
		     					<div className="modal-footer">
		        					<button type="button" id="closeGeckoModal" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
		        					<button type="button" type="submit" className="btn btn-primary">Save changes</button>
		      					</div>
		    				</div>
		  				</div>
					</div>
				</form>
			</div>
    	);
	}
}

export default CryptoTickerForm;