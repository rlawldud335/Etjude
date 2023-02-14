<!-- eslint-disable import/no-extraneous-dependencies -->
<template lang="">
  <div>
    <Dropdown
      :options="options"
      @selected="validateSelection"
      @filter="getDropdownValues"
      :disabled="false"
      placeholder="Please select an animal"
    >
    </Dropdown>
  </div>
</template>
<script>
import { ref } from "vue";
import Dropdown from "vue-simple-search-dropdown";

export default {
  name: "inputDropdown",
  components: {
    Dropdown,
  },
  setup() {
    const options = ref([
      { name: "Cat", id: "cat" },
      { name: "Dog", id: "dog" },
      { name: "Elephant", id: "elephant" },
      { name: "Girafe", id: "girafe" },
      { name: "Snake", id: "snake" },
      { name: "Spider", id: "spider" },
      { name: "Unicorn", id: "unicorn" },
    ]);
    const selected = ref({ name: null, id: null });
    const validateSelection = (selection) => {
      selected.value = selection;
      console.log(`${selection.name} has been selected`);
    };

    const getDropdownValues = (keyword) => {
      console.log(`You could refresh options by querying the API with ${keyword}`);
    };
    return {
      options,
      selected,
      validateSelection,
      getDropdownValues,
    };
  },
};
</script>
<style lang=""></style>
